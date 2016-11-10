# Run in Eclipse or Intellij
#  in "Program Arguments:"
######################################################################################################

import Maven project to the IDE of your choice (note: I'Ve only tried with the above two IDEs: 
preferably "Mars" build for Eclipse or Intellij 2016.1)

run the command "mvn clean install" (either in your IDE or on command line)

Run as Java Application
(choose PageAnalyzerApplication as your main method)
enter below in Project Properties > Run Configurations > Arguments > enter below:
server /YOUR_CHOSEN_IDE_WORKSPACE_DIRECTORY/page-analyzer/src/main/resources/page-admin.yml

/home/justin/DEV/GIT_REPO/EXAMPLES/page-analyzer/target/page-analyzer.jar
######################################################################################################
# Run via command line
######################################################################################################
You should have Java 1.7 or higher correctly installed and configured on your PC!
unzip the rar "PageAnalyzer"

in the command line tool of choice, "cd" to the directory PageAnalyzer
run the command:
java -jar page-analyzer.jar server page-admin.yml
in a browser, enter the following url: localhost:8004//service/entry

**Please note  that the port 8004 should be open on your PC.
If it is already used, simply open the file "page-admin.yml" 
and change the value "port:" to a valid and open port

###############MAIN MENU###############
start page:
http://localhost:8004/service/entry

