/*
 * antUpdate.groovy
 *
 * This is a Groovy 1.7.0 script that dynamically modifies the ant build.xml
 * script used by Netbeans to compile code. This groovy script will modify
 * the build file by adding ant targets for the FindBugs and CheckStyle
 * source code analysis tools. Typically this groovy script will be run
 * on a remote Hudson Continuous Integration server that is pre-configured to
 * access the tool components and their config and library files. However,
 * given a properly configured system, the script can be run locally as well.
 *
 * Authored by Jim Lombardo
 * Version 1.00
 */

final File file = new File('./build.xml')

// determine whether the ant xml contains a target for findbugs.
// if it does, we don't need to modify the ant file
final Node xml = new XmlParser().parse(file)
if(xml.taskdef[0] != null && xml.taskdef[0].attribute("name") == "findbugs") {
   // do nothing, findbugs target already exists
} else {
    // constructor the property for an ant depends
    final projName = xml.@name + "-impl.jar"
    xml = null

    // determine insertion point for new xml
    List antLines = file.readLines()
    int insertLine = 13
    for(i=0; i < antLines.size(); i++) {
        if(antLines[i].contains("import file=")) {
            insertLine = i + 1
            break
        }
    }

    // new xml creates ant targets for findbugs and checkstyle
    final List insertXML = ["",
        '\t<!-- added custom task and target for findbugs -->',
        '\t<taskdef name="findbugs" ',
		'\t\tclassname="edu.umd.cs.findbugs.anttask.FindBugsTask"/>',
        '\t<property name="findbugs.home" value="/java/findbugs" />',
        "",
        '\t<target name="findbugs" depends="' + projName + '">',
        '\t\t<findbugs home="\${findbugs.home}" output="xml" ',
		'\t\toutputFile="findbugs.xml">',
        '\t\t\t<auxClasspath path="\${libraries.dir}" />',
        '\t\t\t<sourcePath path="\${basedir}/src" />',
        '\t\t\t<class location="\${build.classes.dir}" />',
        '\t\t</findbugs>',
        '\t</target>',
		"",
        '\t<!-- added custom task and target for checkstyle -->',
        '\t<taskdef resource="checkstyletask.properties" ',
		'\t\tclasspath="/java/checkstyle/checkstyle-all-5.0.jar" />',
        "",
        '\t<target name="checkstyle">',
        '\t\t<checkstyle config="/java/checkstyle/sun_checks.xml"',
		'\t\t\tfailureProperty="checkstyle.failure"',
		'\t\t\tfailOnViolation="false">',
        '\t\t\t<formatter type="xml" tofile="checkstyle-result.xml"/>',
        '\t\t\t<fileset dir="\${basedir}/src" includes="**/*.java"/>',
        '\t\t</checkstyle>',
        '\t</target>',
		"",
    ]

    // insert into List of original ant xml lines
    antLines.addAll(insertLine,insertXML)

    // now write it all back to the buld file using groovy closures
    new File('./build.xml').withWriter { file2 ->
      antLines.each { line ->
         file2.writeLine(line)
      }
    }

}