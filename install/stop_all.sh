$HADOOP_PREFIX/sbin/hadoop-daemon.sh --script hdfs stop namenode
$HADOOP_PREFIX/sbin/hadoop-daemon.sh --script hdfs stop datanode
$HADOOP_PREFIX/sbin/yarn-daemon.sh stop resourcemanager
$HADOOP_PREFIX/sbin/mr-jobhistory-daemon.sh stop historyserver
$HADOOP_PREFIX/sbin/yarn-daemon.sh stop nodemanager
