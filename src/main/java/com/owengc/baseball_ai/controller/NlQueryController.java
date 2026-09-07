package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.QueryRequest;
import com.owengc.baseball_ai.dto.QueryResult;
import com.owengc.baseball_ai.service.NlQueryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/query", produces = MediaType.APPLICATION_JSON_VALUE)
public class NlQueryController {

    private final NlQueryService nlQueryService;

    public NlQueryController(NlQueryService nlQueryService) {
        this.nlQueryService = nlQueryService;
    }

    @PostMapping
    public QueryResult ask(@RequestBody QueryRequest request) {
        return nlQueryService.ask(request.question());
    }
}