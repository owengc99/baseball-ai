package com.owengc.baseball_ai.controller;

import com.owengc.baseball_ai.dto.QueryRequest;
import com.owengc.baseball_ai.dto.QueryResult;
import com.owengc.baseball_ai.nlquery.NlQueryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Natural Language Query", description = "Ask questions in plain English")
@RestController
@RequestMapping(value = "/api/query", produces = MediaType.APPLICATION_JSON_VALUE)
public class NlQueryController {

    private final NlQueryService nlQueryService;

    public NlQueryController(NlQueryService nlQueryService) {
        this.nlQueryService = nlQueryService;
    }

    @Operation(summary = "Answer a question with generated SQL",
            description = "Generates SQL from the question, validates it, and runs it on a "
                    + "read-only database role. Returns the generated SQL alongside the "
                    + "results. Questions the schema can't answer return UNANSWERABLE.")
    @PostMapping
    public QueryResult ask(@RequestBody QueryRequest request) {
        return nlQueryService.ask(request.question());
    }
}