package com.example.quickpoll.controller;

import java.net.URI;
import java.util.Optional;


import com.example.quickpoll.domain.Poll;
import com.example.quickpoll.dto.error.ErrorDetail;
import com.example.quickpoll.exception.ResourceNotFoundException;
import com.example.quickpoll.repository.PollRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@RestController
@Api(value = "polls", consumes = "Poll API")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @RequestMapping(value="/polls", method=RequestMethod.POST)
    @ApiOperation(value = "Creates a new Poll", notes="The newly created poll Id will be sent in the location response header",
            response = Void.class)
    @ApiResponses(value = {@ApiResponse(code=201, message="Poll Created Successfully", response=Void.class),
            @ApiResponse(code=500, message="Error creating Poll", response= ErrorDetail.class) } )
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls", method=RequestMethod.GET)
    @ApiOperation(value = "Retrieves given Poll", response=Poll.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Poll.class),
            @ApiResponse(code=404, message="Unable to find Poll", response=ErrorDetail.class) } )
    public ResponseEntity<Page<Poll>> getAllPolls(Pageable pageable) {
        Page<Poll> allPolls = pollRepository.findAll(pageable);
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the polls")
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Poll.class)} )
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Optional<Poll> p = pollRepository.findById(pollId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    @ApiOperation(value = "Updates given Poll", response=Void.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),
            @ApiResponse(code=404, message="Unable to find Poll", response=ErrorDetail.class) } )
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Deletes given Poll", response=Void.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),
            @ApiResponse(code=404, message="Unable to find Poll", response=ErrorDetail.class) } )
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.existsById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if(poll == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

}
