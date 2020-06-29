#!/bin/bash --login
cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`
LOGS_DIR=$DEPLOY_DIR/logs
GC_OPTS="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:${LOGS_DIR}/gc.log"
HEAP_DUMP_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOGS_DIR}/heapdump"
JAVA_OPTS+=" $GC_OPTS $HEAP_DUMP_OPTS "
JVM_OPTS="-XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn64m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC"
NACOS_OPTS="-Dnacos.naming.web.context=/ -Dnacos.client.contextPath=/"
CONF_DIR=$DEPLOY_DIR/conf
SERVER_NAME="nacos-test"
DEBUG_ADDEESS=6987

if [ -z "$SERVER_NAME" ]; then
    ECHO_LOG "ERROR: The 'servicename' configuration is null in the bootstrap.yml file!" 3
    exit 1
fi

JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
	if [ -z "$DEBUG_ADDEESS" ]; then
		ECHO_LOG "ERROR: The 'debugport' configuration is null in the bootstrap.yml file!" 3
		exit 1
	fi
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=$DEBUG_ADDEESS,server=y,suspend=n "
fi

PIDS=`ps -ef | grep java | grep $SERVER_NAME |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME alaredy started!"
    exit 1
fi

if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log
LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

echo -e "Starting $SERVER_NAME ...\c"
$JAVA_HOME/bin/java -DgalaxyApplicationName=$SERVER_NAME $JAVA_DEBUG_OPTS $JVM_OPTS $JAVA_OPTS $NACOS_OPTS -classpath $CONF_DIR:$LIB_JARS com.nacos.ConfigController &>$STDOUT_FILE &

PIDS=`ps -f | grep java | grep "$SERVER_NAME" | awk '{print $2}'`
echo "."
echo "$SERVER_NAME is started!"
echo "Using CLASSPATH: $CLASSPATH"
echo "Using JAVA_HONE: $JAVA_HOME"
echo "Using JAVA_VERSION: `$JAVA_HOME/bin/java -version 2>&1 |awk '{a[NR]=$x} END{for(i=1;i<=NR;i++){printf(a[i]" ")}}'|awk '{print $10,$11,$3,$12}'`"
echo "Using JAVA_OPTS: $JAVA_OPTS"
echo "Using JVM_OPTS: $JVM_OPTS"
echo "Using DEPLOY_DIR: $DEPLOY_DIR"
echo "Using LOGS_DIR: $LOGS_DIR"
echo "Using CONF_DIR: $CONF_DIR"
echo "Using STDOUT: $STDOUT_FILE"
echo "PID: $PIDS"
