package com.example.dto;

import com.example.model.Apartment;

public class ApartmentDTO {

	private Long id;
	private Integer number;
	private String description;
	private UserDTO owner;
	private BuildingDTO building;

	public ApartmentDTO() {
	}

	public ApartmentDTO(Apartment apartment) {
		this(apartment.getId(), apartment.getNumber(), apartment.getDescription());

		if (apartment.getOwner() != null) {
			this.owner = new UserDTO(apartment.getOwner());
		}

		if (apartment.getBuilding() != null) {
			this.building = new BuildingDTO(apartment.getBuilding());
		}
	}

	public ApartmentDTO(Integer number, String description) {
		super();
		this.number = number;
		this.description = description;
	}

	public ApartmentDTO(Long id, Integer number, String description) {
		super();
		this.id = id;
		this.number = number;
		this.description = description;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BuildingDTO getBuilding() {
		return building;
	}

	public void setBuilding(BuildingDTO building) {
		this.building = building;
	}

	public static Apartment getApartment(ApartmentDTO apartmentDTO) {
		Apartment apartment = new Apartment(apartmentDTO.getId(), apartmentDTO.getNumber(),
				apartmentDTO.getDescription());
		if (apartmentDTO.getOwner() != null && apartmentDTO.getOwner().getId() != null) {
			apartment.setOwner(UserDTO.getUser(apartmentDTO.getOwner()));
		}
		if (apartmentDTO.getBuilding() != null && apartmentDTO.getBuilding().getId() != null) {
			apartment.setBuilding(BuildingDTO.getBuilding(apartmentDTO.getBuilding()));
		}
		return apartment;
	}
}
