package com.example.petstore.db;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.example.petstore.Pet;

@ApplicationScoped
public class DB {
	private static List<Pet> petTable=new ArrayList<Pet>();
	//private static List<PetType> petTypeTable=new ArrayList<PetType>();
	
	static int id=1;
	
	public static int getPetsCount() {
		return petTable.size();
	}
	
	public static List<Pet> getPetTable(){
		return petTable;
	}

	public static Pet savePet(Pet pet){
		pet.setPetId(id++);
		petTable.add(pet);
		return pet;
	}
	
	public static Pet getPet(int id) {
		return petTable.get(id-1);
	}
	
	public static Pet updatePet(Pet pet) {
		petTable.set(pet.getPetId()-1, pet);
		return petTable.get(pet.getPetId()-1);
	}
	
	public static int deletePet(int petId) {
		petTable.remove(petId-1);
		return 1;
	}
}
