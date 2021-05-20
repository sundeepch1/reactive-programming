package com.skc.webflux.handler;

import com.skc.webflux.dao.CustomerDao;
import com.skc.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customerFlux = customerDao.getCustomerList();
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }
}
