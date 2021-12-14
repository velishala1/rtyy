package com.basic.contrloller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PenController {
	@Autowired
	PenDao pd;

	@RequestMapping(value = "/")
	public String show() {
		return "indian";

	}

	@RequestMapping(value = "/all")
	public ResponseEntity<List<Pen>> getAllItems() {
		System.out.println("Get all Methos");
		return new ResponseEntity<List<Pen>>(pd.getAll(), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public Pen get(@RequestParam("itemId") int id) {
		return pd.getIndiviualItem(id);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> addDetails(@RequestBody Pen p) {
		String message = null;
		if (pd.addItem(p.getName(), p.getCategory()) >= 1) {
			message = "data seved";

		} else {
			message = "please chech";
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String DeleteDetails(@PathVariable("id") int id) {
		if (pd.deleteItem(id) >= 1) {
			return "Date removed succesfully";
		} else {
			return "pleace check";
		}
	}
	

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public String updateDetails(@PathVariable("id") int id, @RequestBody Pen p) {
		if (pd.updateItem(id, p.getName(), p.getCategory()) >= 1) {
			return "data update succesfully";
		} else {
			return "please check";
		}
	}

}
