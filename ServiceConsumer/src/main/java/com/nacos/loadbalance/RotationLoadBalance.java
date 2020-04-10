package com.nacos.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class RotationLoadBalance implements LoadBalancer{
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances) {
        int index = atomicInteger.incrementAndGet()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
