package com.jasongj.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class HashPartitioner implements Partitioner {

  public HashPartitioner(VerifiableProperties verifiableProperties) {}

  @Override
  public int partition(Object key, int numPartitions) {
    if ((key instanceof Integer)) {
      return Math.abs(Integer.parseInt(key.toString())) % numPartitions;
    }
    return Math.abs(key.hashCode() % numPartitions);
  }
}


