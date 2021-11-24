package com.example.petstore.db;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.example.petstore.Pet;

@ApplicationScoped
public class DB {
	private static List<Pet> petTable=new ArrayList<Pet>();
	private static List<String> petTypeTable=new ArrayList<String>();
	
	static int id=1;
	
	public static int getPetsCount() {
		return petTable.size();
	}
	
	public static List<Pet> getPetTable(){
		return petTable;
	}
	
	public static List<String> getPetTypeTable(){
		return petTypeTable;
	}

	public static Pet savePet(Pet pet){
		pet.setPetId(id++);
		petTable.add(pet);
		//if pet type already in the type table, type not added again
		for (String petType : petTypeTable) {
			if (pet.getPetType().equalsIgnoreCase(petType)) {
				return pet;
			}
		}
		petTypeTable.add(pet.getPetType());
		return pet;
	}
	
	public static Pet getPet(int id) {
		return petTable.get(id-1);
	}
	
	public static Pet updatePet(Pet pet) {
		int petTypeCount=0;
		int typeExists=0;
		String previousType=petTable.get(pet.getPetId()-1).getPetType();
		
		//check the count of pets with the same type of the updating pet
		for (Pet singlePet : petTable) {
			if (singlePet.getPetType().equalsIgnoreCase(previousType)) {
				petTypeCount=petTypeCount+1;
			}
		}
		
		petTable.set(pet.getPetId()-1, pet);
		
		//check whether the updating pet type is already available or not
		for (String petType : petTypeTable) {
			if (pet.getPetType().equalsIgnoreCase(petType)) {
				typeExists=1;
			}
		}
		
		//if only the updating pet has the previous pet type
		if(petTypeCount==1) {
			int i=0;
			//find the location of the type
			for (String petType : petTypeTable) {
				if (previousType.equalsIgnoreCase(petType)) {
					break;
				}
				i++;
			}
			
			//if the new updating pet type is a new one, change the previous type to new one
			if(typeExists==0) {
				petTypeTable.set(i, pet.getPetType());
			
			//if the new updating pet type already exists and the updating type is differ from the previous, previous type removed	
			}else if(typeExists==1 && !pet.getPetType().equalsIgnoreCase(previousType)) {
				petTypeTable.remove(i);
			}
		//if more than one pet has the previous pet type
		}else {
			//add only the type is a new one
			if(typeExists==0) {
				petTypeTable.add(pet.getPetType());
			}
		}
		
		return petTable.get(pet.getPetId()-1);
	}
	
	public static int deletePet(int petId) {
		int petTypeCount=0;
		String removeType=petTable.get(petId-1).getPetType();
		
		//check the count of pets with the same type of the deleting pet's type
		for (Pet singlePet : petTable) {
			if (singlePet.getPetType().equalsIgnoreCase(removeType)) {
				petTypeCount=petTypeCount+1;
			}
		}
		
		//if only the deleting pet has that type
		if(petTypeCount==1) {
			int i=0;
			
			for (String petType : petTypeTable) {
				if (removeType.equalsIgnoreCase(petType)) {
					break;
				}
				i++;
			}
			petTypeTable.remove(i);
		}
		
		petTable.remove(petId-1);
		
		//Id of the pets that is after the deleted pet is reduce by one and the id count is also reduce by one
		for (int i = petId-1; i < petTable.size(); i++) {
			petTable.get(i).setPetId(i+1);
	    }
		id--;
		
		return 1;
	}
}
