package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Glitch;
import com.example.model.GlitchState;
import com.example.model.GlitchType;
import com.example.model.User;
import com.example.repository.GlitchRepository;
import com.example.repository.GlitchStateRepository;
import com.example.repository.GlitchTypeRepository;
import com.example.repository.UserRepository;

@Service
public class GlitchService {

	@Autowired
	GlitchRepository glitchRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	GlitchTypeRepository glitchTypeRepository;
	@Autowired
	GlitchStateRepository glitchStateRepository;

	public Glitch saveNewGlitch(Glitch glitch, String state) {
		GlitchState glitchState = glitchStateRepository.findByState(state);
		glitch.setState(glitchState);
		glitch.setDateOfReport(new Date());
		return glitchRepository.save(glitch);
	}

	public Glitch save(Glitch glitch) {
		return glitchRepository.save(glitch);
	}

	public Page<Glitch> findGlitches(Pageable page, User user) {
		List<String> authority = userRepository.getUserAuthority(user.getId());
		if (authority.contains("ROLE_USER") || authority.contains("ROLE_OWNER")) {
			return glitchRepository.findGlitchesOfTenant(user.getId(), page);
		} else {
			return glitchRepository.findGlitchesOfCompany(user.getId(), page);
		}
	}

	public Glitch findOne(Long id) {
		return glitchRepository.findOne(id);
	}

	public GlitchType saveGlitchType(GlitchType glitchType) {
		return glitchTypeRepository.save(glitchType);
	}

	public GlitchType findOneGlitchType(Long id) {
		return glitchTypeRepository.findOne(id);
	}

	public List<GlitchType> findAllGlitchType() {
		return glitchTypeRepository.findAll();
	}

	public void removeGlitchType(Long id) {
		glitchTypeRepository.delete(id);
	}

	public GlitchType findOneGlitchTypeByName(String name) {
		return glitchTypeRepository.findGlitchTypeByName(name);
	}

	public Page<Glitch> findByResponsibility(Pageable page, Long id) {
		return glitchRepository.findGlitchByResponsiblePerson(id, page);
	}

	public List<Glitch> findWithoutMeeting() {
		return glitchRepository.findWithoutMeeting();
	}

	public List<Glitch> findActiveGlitches(Long id) {
		return glitchRepository.findActiveGlitches(id);
	}

	public List<Glitch> findPendingGlitches(Long id) {
		return glitchRepository.findPendingGlitches(id);
	}

	public Integer getCountOfGlitches(User user) {
		List<Glitch> glitches = glitchRepository.findGlitchesOfTenantAll(user.getId());
		return glitches.size();
	}
}
