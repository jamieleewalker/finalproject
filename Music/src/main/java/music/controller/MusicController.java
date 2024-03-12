package music.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.controller.model.ArtistData;
import music.service.MusicService;

@RestController
@RequestMapping("/music")
@Slf4j
public class MusicController {
	
	@Autowired
	private MusicService musicService;
	
	@PostMapping("/music")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData createArtist(@RequestBody ArtistData artistData) {
		log.info("Creating artist {}", artistData);
		return musicService.saveArtist(artistData);
	}
	
	@PutMapping("/artist/{artistId}")
	public ArtistData updateArtist(@PathVariable Long artistId, @RequestBody ArtistData artistData) {
		artistData.setArtistId(artistId);
		log.info("Updating artist {}", artistData);
		return musicService.saveArtist(artistData);
	}
	
	@GetMapping("/artist/{artistId}")
	public ArtistData retrieveArtist(@PathVariable Long artistId) {
		log.info("Retrieving artist with ID={}", artistId);
		return musicService.retrieveArtistById(artistId);
	}
	
	@GetMapping("/artist")
	public List<ArtistData> retrieveAllArtist() {
		log.info("Retrieving all artists.");
		return musicService.retrieveAllArtist();
	}
	
	@DeleteMapping("/artist/{artistId}")
	public Map<String, String> deleteArtist(@PathVariable Long artistId) {
		log.info("Deleting artist with ID=" + artistId + ".");
		musicService.deleteArtist(artistId);
		
		return Map.of("message", "Artist with ID=" + artistId + " was deleted successfully.");
	}
}