package br.com.questions.service;

import br.com.questions.entity.Customer;
import br.com.questions.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public void includeConsumer(Customer customer) {
        this.repository.save(customer);
    }
}
