#!/bin/sh

MAIN_CLASS="fx.pressurefx.Main"
TARGET="target/classes"

CLASSPATH="."
CLASSPATH=${CLASSPATH}":/home/alexey/jlib/postgresql-42.7.9.jar"
export CLASSPATH

JFX_PATH="/home/alexey/jfx"
JFX_MOD="javafx.controls,javafx.fxml"

if [ -d ${TARGET} ]; then
    (cd ${TARGET} && java -p ${JFX_PATH} --add-modules ${JFX_MOD} ${MAIN_CLASS})
else
    echo "NO compiled APP target/classes!"
fi


