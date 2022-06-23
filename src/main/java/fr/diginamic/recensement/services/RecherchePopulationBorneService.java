package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws Exception {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		if (choix == null || choix.equals(""))
			throw new Exception("Code département incorrect");
		
		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		int min = parseIntOr(saisieMin, "Saisie min incorrecte") * 1000;
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		int max = parseIntOr(saisieMax, "Saisie max incorrecte") * 1000;
		
		if (min < 0 || max < 0 || min > max)
			throw new Exception("Saisie min/max incorrecte");
		
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
	}

	private int parseIntOr(String in, String error) throws Exception
	{
		try
		{
			return Integer.parseInt(in);
		}
		catch (NumberFormatException e)
		{
			throw new Exception(error);
		}
	}
}
