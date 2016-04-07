$HADOOP_PREFIX/sbin/hadoop-daemon.sh --script hdfs start namenode
$HADOOP_PREFIX/sbin/hadoop-daemon.sh --script hdfs start datanode
$HADOOP_PREFIX/sbin/yarn-daemon.sh start resourcemanager
$HADOOP_PREFIX/sbin/mr-jobhistory-daemon.sh start historyserver
$HADOOP_PREFIX/sbin/yarn-daemon.sh start nodemanager
