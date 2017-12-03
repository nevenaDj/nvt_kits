package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.GlitchDTO;
import com.example.dto.GlitchTypeDTO;
import com.example.dto.UserDTO;
import com.example.model.Apartment;
import com.example.model.Building;
import com.example.model.Glitch;
import com.example.model.GlitchType;
import com.example.model.User;
import com.example.security.TokenUtils;
import com.example.service.ApartmentService;
import com.example.service.BuildingService;
import com.example.service.GlitchService;
import com.example.service.UserService;

@RestController
public class GlitchController {

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	GlitchService glitchService;

	@Autowired
	UserService userService;

	@Autowired
	ApartmentService apartmentService;

	@Autowired
	BuildingService buildingService;

	@RequestMapping(value = "/apartments/{id}/glitches", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OWNER')")
	public ResponseEntity<GlitchDTO> addGlitch(@PathVariable Long id, @RequestBody GlitchDTO glitchDTO,
			HttpServletRequest request) {
		String token = request.getHeader("X-Auth-Token");
		String username = tokenUtils.getUsernameFromToken(token);

		Glitch glitch = GlitchDTO.getGlitch(glitchDTO);

		User company = userService.findOne(glitchDTO.getCompanyID());

		if (company != null) {
			glitch.setCompany(company);
		}

		User tenant = userService.findByUsername(username);
		glitch.setTenant(tenant);

		Apartment apartment = apartmentService.findOne(id);

		if (apartment == null) {
			return new ResponseEntity<GlitchDTO>(HttpStatus.BAD_REQUEST);
		}

		glitch.setApartment(apartment);

		if (glitch.getResponsiblePerson() == null) {
			Building building = buildingService.findOne(apartment.getBuilding().getId());
			glitch.setResponsiblePerson(building.getPresident());
		}

		glitch = glitchService.saveNewGlitch(glitch, "REPORTED");

		return new ResponseEntity<GlitchDTO>(new GlitchDTO(glitch), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/glitches", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OWNER', 'ROLE_COMPANY')")
	public ResponseEntity<List<GlitchDTO>> getGlitches(Pageable page, HttpServletRequest request) {
		String token = request.getHeader("X-Auth-Token");
		String username = tokenUtils.getUsernameFromToken(token);

		User user = userService.findByUsername(username);

		Page<Glitch> glitches = glitchService.findGlitches(page, user);

		List<GlitchDTO> glitchesDTO = new ArrayList<GlitchDTO>();

		for (Glitch glitch : glitches) {
			glitchesDTO.add(new GlitchDTO(glitch));
		}

		return new ResponseEntity<List<GlitchDTO>>(glitchesDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/glitches/{id}/responsiblePerson", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_PRESIDENT')")
	public ResponseEntity<GlitchDTO> changeResponsiblePerson(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		Glitch glitch = glitchService.findOne(id);

		if (glitch == null) {
			return new ResponseEntity<GlitchDTO>(HttpStatus.BAD_REQUEST);
		}

		User user = userService.findByUsername(userDTO.getUsername());

		if (user != null) {
			glitch.setResponsiblePerson(user);
		}

		glitch = glitchService.save(glitch);

		return new ResponseEntity<GlitchDTO>(new GlitchDTO(glitch), HttpStatus.OK);

	}

	@RequestMapping(value = "/glitchTypes", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<GlitchTypeDTO> addGlitchType(@RequestBody GlitchTypeDTO glitchTypeDTO) {
		GlitchType glitchType = GlitchTypeDTO.getGlitchType(glitchTypeDTO);

		glitchType = glitchService.saveGlitchType(glitchType);

		return new ResponseEntity<GlitchTypeDTO>(new GlitchTypeDTO(glitchType), HttpStatus.CREATED);
	}
}
