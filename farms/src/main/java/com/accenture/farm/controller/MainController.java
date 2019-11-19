package com.accenture.farm.controller;

import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.farm.model.*;
import com.accenture.farm.model.Egg.estadoHuevo;
import com.accenture.farm.repository.*;

@RestController
@RequestMapping({ "/granja" })

public class MainController {

	@Autowired
	private DaoChicken daochick;

	@Autowired
	private DaoEgg daoegg;

	@Autowired
	private DaoFarm daofarm;

	Random ran_c = new Random();

	/**
	 * APIS QUE NECESITO *
	 * 
	 *
	 * 
	 * 
	 * HECHOS CREAR GRANJA GETGRANJAS GETGALLINAS GETHUEVOS
	 * 
	 * DELETEGALLINA UPDATE HUEVO
	 * 
	 * POST GALLINA(IDGRANJA) POST HUEVO(iD GALLINA)
	 * 
	 */

	// API PARA CREAR GRANJAS
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = { "/nuevagranja" })
	public Farm create(@RequestBody Farm farm) {
		return daofarm.save(farm);

	}

	// AGREGA GALLINAS A LA GRANJA DE ID FARMID
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({ "{farmId}/nuevagallina" })
	public ResponseEntity<Object> createChicken(@RequestBody Chicken chicken, @PathVariable("farmId") long id_farm) {
		Farm far = daofarm.findById(id_farm).orElse(null);
		Chicken chick = new Chicken();

		if (far != null) {
			chick.setName(chicken.getName());
			chick.setFarm(far);
			chick.setHuevos(null);

			Chicken chi = daochick.save(chick);

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("gallina", chi.getName());

			return ResponseEntity.ok().body(obj.toString());

		} else {

			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("Message", "No se encontro la granja");

			return ResponseEntity.ok().body(obj.toString());
		}
	}

	// AGREGA UN HUEVO A LA GALLINA CHICKID
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({ "/{chickId}/nuevohuevo" })
	public ResponseEntity<Object> createEgg(@PathVariable Long chickId) {

		Chicken chick = daochick.findById(chickId).orElse(null);

		Egg egg1 = new Egg();

		if (chick != null) {
			egg1.setChick(chick);
			egg1.setEstadoVivo();

			int ran_cint = ran_c.nextInt(3);

			switch (ran_cint) {
			case 0:
				egg1.setBlanco();
				break;
			case 1:
				egg1.setMarr();
				break;
			case 2:
				egg1.setNegro();
				break;
			}

			Egg egg2 = daoegg.save(egg1);

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("results: Egg id:", egg2.getId());

			return ResponseEntity.ok().body(obj.toString());
		} else {
			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("message", "Chicken not found"); // aca muestro un mensaje diciendo que el chicken no se encontro

			return ResponseEntity.ok().body(obj.toString());
		}
	}

	// LISTA TODAS LAS GRANJAS
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({ "/listargranjas" })
	public ResponseEntity<Object> getListFarm() {

		JSONArray json_array = new JSONArray();

		JSONObject obj = new JSONObject();

		List<Farm> farmFound = daofarm.findAll();

		if (farmFound.size() > 0) {
			for (Farm fa : farmFound) {

				JSONObject aux = new JSONObject();

				System.out.println("Nombre: " + fa.getName());
				aux.put("name", fa.getName());
				aux.put("id", fa.getId());
				System.out.println("cant gallinas" + fa.getHens().size());
				aux.put("cantGallinas", fa.getHens().size());

				System.out.println("cant huevos" + fa.getEggs().size());

				aux.put("cantHuevosHUERFANOS", fa.getEggs().size());
//				aux.put("cantPollitos", fa.getPollitos().size());
				aux.put("huevosTortilla", fa.getTortilla().size());
				System.out.println(aux);
				json_array.put(aux);
			}
			System.out.println(json_array);
			obj.put("error", 0);
			obj.put("list", json_array);

		} else {

			obj.put("error", 1);
			obj.put("message", "No se encontraron granjas");

			// return ResponseEntity.ok().body(obj.toString())
		}
		// System.out.println("Aca andaFin");
		return ResponseEntity.ok().body(obj.toString());

	}

	// LISTA TODAS LAS GALLINAS DE LA GRANJA ID
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = { "/listagallinas/{id}" })
	public ResponseEntity<Object> getListaGallinas(@PathVariable Long id) {

		Farm farm = daofarm.findById(id).orElse(null);
		List<Chicken> chickFound = daochick.findByFarm(farm);
		List<Egg> eggFound = daoegg.findByFarm(farm);

		JSONArray json_array_huevos = new JSONArray();
		JSONArray json_array = new JSONArray();

		if (chickFound.size() > 0 || eggFound.size() > 0) {
			if (chickFound.size() > 0) {
				for (Chicken chi : chickFound) {
					JSONObject aux = new JSONObject();
					aux.put("id", chi.getId());
					aux.put("name", chi.getName());
					aux.put("eggs", chi.getHuevos().size());
					json_array.put(aux);
				}
			}
			if (eggFound.size() > 0) {
				for (Egg egg : eggFound) {
					JSONObject aux = new JSONObject();
					aux.put("id", egg.getId());
					aux.put("status", egg.getEstado());
					aux.put("color", egg.getColor());
					json_array_huevos.put(aux);
				}
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("list", json_array);
			obj.put("name", farm.getName());
			obj.put("huevoshuerfanos", json_array_huevos);

			return ResponseEntity.ok().body(obj.toString());

		} else {
			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("message", "No se encontraron gallinas");
			obj.put("name", farm.getName());

			return ResponseEntity.ok().body(obj.toString());
		}
	}

	// LISTA TODOS LOS HUEVOS DE LA GALLINA ID
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = { "/{id}/listahuevos" })
	public ResponseEntity<Object> getListaHuevos(@PathVariable Long id) {
		Chicken chick = daochick.findById(id).orElse(null);
		List<Egg> eggFound = daoegg.findByChick(chick);

		JSONArray json_array = new JSONArray();

		if (eggFound.size() > 0) {
			for (Egg huevo : eggFound) {
				JSONObject aux = new JSONObject();
				aux.put("id", huevo.getId());
				aux.put("color", huevo.getColor());
				aux.put("status", huevo.getEstado());
				json_array.put(aux);
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("list", json_array);
			obj.put("name", chick.getName());

			return ResponseEntity.ok().body(obj.toString());

		} else {
			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("message", "No se encontraron huevos");
			obj.put("name", chick.getName());

			return ResponseEntity.ok().body(obj.toString());
		}
	}

	// BORRAR GALLINITA. MATAR MATAR MATAR
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping({ "{farmId}/farm/{chickId}/kill" })
	public ResponseEntity<Object> deleteChick(@PathVariable("chickId") Long chickId,
			@PathVariable("farmId") Long farmId) {

		JSONObject obj = new JSONObject();

		Farm farm = daofarm.findById(farmId).orElse(null);
		Chicken chick = daochick.findById(chickId).orElse(null);

		if (chick != null) {
			List<Egg> eggFound = daoegg.findByChick(chick);
//		    Cambia el ID de la lista de huevos, se le asigna farmID, huevos huerfanos
			if (eggFound.size() > 0) {

				// daofarm.save(farm);

				for (Egg huevo : eggFound) {

					String estadoHuevo = huevo.getEstado().toString();

					System.out.println(huevo.getEstado() + "SO YOOOOOOOOOOO");

					if (estadoHuevo == "TORTILLA" || estadoHuevo == "NACIDO") {
						if (estadoHuevo == "TORTILLA") {
							huevo.setChick(null);
							huevo.setFarm(farm);
							daoegg.save(huevo);
							farm.tortilla.add(huevo);
							daofarm.save(farm);
							System.out.println("lista tortillas " + farm.getTortilla().size());

						} else {
							huevo.setChick(null);
							huevo.setFarm(farm);
							daoegg.save(huevo);
//							farm.pollitos.add(huevo);
							daofarm.save(farm);
//							System.out.println("lista pollitos " + farm.pollitos.size());
						}

						/*
						 * System.out.println(huevo.getEstado()+"SO YOOOOOOOOOOO DENTRO DEL IF");
						 * aux.put("error", 0); aux.put("message",
						 * "Estado del huevo es TORTILLA o NACIDO"); obj.put("prueba",aux.toString());
						 */

					} else {

						/*
						 * System.out.println(huevo.getEstado()+"SO YOOOOOOOOOOO DENTRO DEL ELSE");
						 * System.out.println("estado del huevo. "+huevo.getEstado());
						 */
						huevo.setChick(null);
						huevo.setFarm(farm);
						daoegg.save(huevo);
						farm.eggs.add(huevo);
						daofarm.save(farm);
						System.out.println("setea nueva FK al huevo");
					}
				} // for

				daochick.deleteById(chickId);

				return ResponseEntity.ok().body(obj.toString());

			} else {

				daochick.deleteById(chickId);
				obj.put("error", 0);
				obj.put("message", "La gallina está en la olla");
//				obj.put("cantPollitos", farm.pollitos.size());
				obj.put("cantHuerfanos", farm.eggs.size());
				obj.put("cantTortillas", farm.tortilla.size());

				// return ResponseEntity.ok().body(obj.toString());
			}

		} else {

			obj.put("error", 1);
			obj.put("message", "Error al borrar la gallina");

			return ResponseEntity.ok().body(obj.toString());
		}

		return ResponseEntity.ok().body(obj.toString());
	}

	// BORRAR GRANJA. MATAR MATAR MATAR
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping({ "{farmId}/deletefarm" })
	public ResponseEntity<Object> deleteFarm(@PathVariable("farmId") Long farmId) {

		JSONObject obj = new JSONObject();
		Farm farm = daofarm.findById(farmId).orElse(null);

		if (farm != null) {
			List<Chicken> chickFound = daochick.findByFarm(farm);
			List<Egg> eggfound = daoegg.findByFarm(farm);

			for (Chicken chi : chickFound) {
				daochick.deleteById(chi.getId());
			}

			obj.put("error", 0);
			obj.put("message", "Lista de gallinas borrada");

			for (Egg huevin : eggfound) {
				daoegg.deleteById(huevin.getId());
			}

			obj.put("error", 0);
			obj.put("message", "Lista de huevos borrada");

			daofarm.deleteById(farmId);

			obj.put("error", 0);
			obj.put("message", "Granja borrada");

		} else {
			obj.put("error", 1);
			obj.put("message", "Error al borrar granja");
		}

		return ResponseEntity.ok().body(obj.toString());
	}

	// HACE UN UPDATE DEL ESTADO DEL HUEVO CON eggID
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({ "/updatehuevo/{eggId}" })
	public ResponseEntity<Object> updateEgg(@PathVariable("eggId") Long eggId, @RequestBody InputCE_Egg cambioEstado) {

		JSONObject obj = new JSONObject();
		JSONObject aux = new JSONObject();

		// Chicken chick = daochick.findById(chickId).orElse(null);
		Egg egguito = daoegg.findById(eggId).orElse(null);

		if (egguito != null) {
			switch (cambioEstado.getEstado()) {
			case 0:
				egguito.setEstadoTortilla();
				break;
			case 1:
				egguito.setEstadoNacido();
				break;
			default:
				aux.put("message", "Estado incorrecto");
			} // switch
			Egg egg2 = daoegg.save(egguito);

			obj.put("error", 0);
			obj.put("results", egg2.getId());
		} else {

			obj.put("error", 1);
			obj.put("message", aux);
		}
		return ResponseEntity.ok().body(obj.toString());
	}

	// EN CONSTRUCCION. VUELVA PRONTO
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = { "/{id}/pruebas" })
	public ResponseEntity<Object> getListaHuevosSSSSSSSSSS(@PathVariable Long id) {

		Chicken chick = daochick.findById(id).orElse(null);
		List<Egg> eggFound = daoegg.findByChick(chick);

		JSONArray json_array = new JSONArray();

		if (eggFound.size() > 0) {
			for (Egg huevo : eggFound) {
				JSONObject aux = new JSONObject();
				aux.put("id", huevo.getId());
				aux.put("color", huevo.getColor());
				aux.put("status", huevo.getEstado());

				String prueba = huevo.getEstado().toString();
				System.out.println(prueba.toString());

				if (prueba == "VIVO") {
					System.out.println("entro si");
				} else {
					System.out.println("entra else");
				}
				aux.put("PRUEBA", prueba);

				json_array.put(aux);
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("list", json_array);
			obj.put("name", chick.getName());

			return ResponseEntity.ok().body(obj.toString());

		} else {
			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("message", "No se encontraron huevos");
			obj.put("name", chick.getName());

			return ResponseEntity.ok().body(obj.toString());
		}
	}

	// Update de nombre de gallina Body
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({ "/updategallinanombre/{chickId}" })
	public ResponseEntity<Object> updateChickName(@PathVariable("chickId") Long chickId,
			@RequestBody InputGallina inchick) {

		JSONObject obj = new JSONObject();

		Chicken chick = daochick.findById(chickId).orElse(null);

		if (chick != null) {

			chick.setName(inchick.getNombre());
			daochick.save(chick);

			obj.put("error", 0);
			obj.put("results", chick.getName());

		} else {

			obj.put("error", 1);
			obj.put("message", "No se encontro la gallina con id: " + chickId);

		}
		return ResponseEntity.ok().body(obj.toString());

	}

	// UPdate crear/sacar huevos

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({ "/updategallinahuevos/{chickId}" })
	public ResponseEntity<Object> updateChickEggs(@PathVariable("chickId") Long chickId,
			@RequestBody InputGallina inchick) {
		JSONObject obj = new JSONObject();
		
		Chicken chick = daochick.findById(chickId).orElse(null);

		//inchick.huevos me dice el numero de huevos que deberia tener la gallina al final del post
		if (chick != null) {
			int diff=compararHuevos(chick.getHuevos().size(),inchick.getHuevos());
			if(diff>0) {
				System.out.println("Positivo diff: "+diff);
				agregarHuevos(diff, chickId);
				obj.put("message", "Se agregaron: "+diff+" Huevos");
			}else {
				diff=diff*-1;
				System.out.println("Negativo diff: "+diff);
				sacarHuevos(diff, chickId);
				System.out.println("Negativo diff2: "+diff);
				obj.put("message", "Se quitaron: "+diff+" Huevos");
			}		
			obj.put("error", 0);
			
		}else {
			
			obj.put("error", 1);
			obj.put("message", "No se encontro la gallina con id: " + chickId);
		}
		

		return ResponseEntity.ok().body(obj.toString());
	}
	
	//Compara diferencia entre huevos. diff>0: agregar, diff<0: sacar,diff=0 no Diff
	private int compararHuevos(int eggchick, int egginput ) {
		int diff=0;
		if(eggchick < egginput) {
			diff=egginput - eggchick;
			System.out.println("comparar huevitos T:"+diff);
			
			return diff; 
		}else {
			diff= eggchick - egginput;
			diff=diff*-1;
			System.out.println("comparar huevitos F:"+diff);
			return diff;
		}
	}
	
	//Agrega Huevos hasta satisfacer Diff
	private void agregarHuevos(int diff, Long chickid) {
		for (int i=0;i<diff;i++) {
			createEgg(chickid);
			System.out.println(i);
		}
		
	}
	
	//Borra Huevos hasta satisfacer Diff
	private void sacarHuevos(int diff, Long chickId) {
		Chicken chick = daochick.findById(chickId).orElse(null);
		List<Egg> eggs = daoegg.findByChick(chick);
		System.out.println("Diff sacar:++ "+diff);
		while(diff>0) {
			Egg egg = new Egg();
			egg=eggs.get(eggs.size() -1);
			//egg.getEstado();
			egg.setChick(null);
			egg.setFarm(chick.getFarm());
			egg.setEstado(estadoHuevo.NOVISIBLE);
			daoegg.save(egg);
			//System.out.println("Antes de diff-- : "+diff);
			diff--;
			System.out.println("desp de diff-- : "+diff);
			
		}
	}
	
}// MAIN CONTROLLER
