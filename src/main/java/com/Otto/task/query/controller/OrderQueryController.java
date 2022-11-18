package com.Otto.task.query.controller;

import com.Otto.task.lib.entity.State;
import com.Otto.task.query.queries.GetOrdresStateQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/order")
public class OrderQueryController {

    private QueryGateway queryGateway;

    @GetMapping("/{id}")
    public ResponseEntity getOrderState(@PathVariable String id) {
        try {
            GetOrdresStateQuery getOrdresStateQuery =
                    new GetOrdresStateQuery(id);

            State state = queryGateway.query(
                            getOrdresStateQuery,
                            ResponseTypes.instanceOf(State.class))
                    .join();
            return new ResponseEntity(state.toString(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}