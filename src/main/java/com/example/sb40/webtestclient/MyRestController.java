package com.example.sb40.webtestclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class MyRestController { // implements MyRestInterface {

    private static final Logger LOG = LoggerFactory.getLogger(MyRestController.class);

    /**
     * Sample usage: "curl $HOST:$PORT/test/1".
     * @param productId
     * @return
     */
    @GetMapping(
            value = "/test/{productId}",
            produces = TEXT_PLAIN_VALUE)
    public String getValue(@PathVariable int productId) {
        LOG.info("/test will return: {}", productId);
        return "Returned value: " + productId + "\n";
    }

    /**
     * Sample usage, see below.
     *
     * curl -X POST $HOST:$PORT/product-composite \
     *   -H "Content-Type: application/json" --data \
     *   '{"productId":123,"name":"product 123","weight":123}'
     *
     * @param body A JSON representation of the new composite product
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(
            value    = "/product-composite",
            consumes = "application/json")
    int createProduct(@RequestBody ProductAggregate body) {
        LOG.info("### receiving productId: {}", body.getProductId());
        return body.getProductId();
    }

}