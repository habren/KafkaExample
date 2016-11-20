package com.jasongj.kafka;

import java.util.concurrent.atomic.AtomicLong;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class RoundRobinPartitioner implements Partitioner {
  
  private static AtomicLong next = new AtomicLong();

  public RoundRobinPartitioner(VerifiableProperties verifiableProperties) {}

  @Override
  public int partition(Object key, int numPartitions) {
    long nextIndex = next.incrementAndGet();
    return (int)nextIndex % numPartitions;
  }
}


