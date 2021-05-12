package org.society.controller;

import java.util.List;

import org.society.entities.RegisteredSocietyVoters;
import org.society.entities.User;
import org.society.service.RegisteredSocietyVotersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RegisteredSocietyVoters")
public class RegisteredSocietyVotersController {

	@Autowired
	RegisteredSocietyVotersServiceImpl serviceImpl;

	public void setServiceImpl(RegisteredSocietyVotersServiceImpl serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	@GetMapping(value="/all",produces="application/json")
	public ResponseEntity<List<RegisteredSocietyVoters>> getVoters()
	{
		return new ResponseEntity<List<RegisteredSocietyVoters>>(serviceImpl.viewRegisteredVoterList(),HttpStatus.OK);
	}

	@GetMapping(value="/{Id}",produces="application/json")
	public ResponseEntity<RegisteredSocietyVoters> findUser(@PathVariable("Id")int Id){
		RegisteredSocietyVoters voters = null;

		voters = serviceImpl.searchByVoterID(Id);

		if(voters==null) {
			System.out.println("voters not found");
			return new ResponseEntity("Sorry! voter not found!",HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RegisteredSocietyVoters>(voters, HttpStatus.OK);
	}

	@PostMapping(consumes="application/json")
	public ResponseEntity<HttpStatus> addRegisterVoters(@RequestBody RegisteredSocietyVoters voters)
	{
		serviceImpl.voterRegistration(voters);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@PutMapping(consumes="application/json")
	public ResponseEntity<HttpStatus> modifyVoter(@RequestBody RegisteredSocietyVoters voter)
	{
		boolean a = false;

		a= serviceImpl.updateRegisteredVoterDetails(voter);

		if(a == false) 
		{
			System.out.println("voters not found");
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	@DeleteMapping(value="/{Id}")
	public ResponseEntity<HttpStatus> deletevoter(@PathVariable("Id")Integer Id)
	{
		boolean a= false;

		a=serviceImpl.deleteRegisteredVoter(Id);

		if(a==false)
		{
			System.out.println("voter not found to delete");
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

}




