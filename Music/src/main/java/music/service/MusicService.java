package music.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.controller.model.ArtistData;
import music.dao.ArtistDao;
import music.entity.Artist;

@Service
public class MusicService {
	@Autowired
	private ArtistDao artistDao;
	
	@Transactional(readOnly = false)
	public ArtistData saveArtist(ArtistData artistData) {
		Artist artist = artistData.toArtist();
		Artist dbArtist = artistDao.save(artist);
		
		return new ArtistData(dbArtist);
		}
	
	@Transactional(readOnly = true)
	public ArtistData retrieveArtistById(Long artistId) {
		Artist artist = findArtistById(artistId);
		return new ArtistData(artist);
	}
	
	private Artist findArtistById(Long artistId) {
		return artistDao.findById(artistId).orElseThrow(() 
				-> new NoSuchElementException("artist with ID=" + artistId + " was not found"));
	}
	@Transactional(readOnly = true)
	public List<ArtistData> retrieveAllArtist() {
		List<Artist> artistEntities = artistDao.findAll();
		List<ArtistData> artistDtos = new LinkedList<>();
		
		artistEntities.sort((art1, art2) -> art1.getFirstName().compareTo(art2.getFirstName()));
		
		for(Artist artist : artistEntities) {
			ArtistData artistData = new ArtistData(artist);
			artistDtos.add(artistData);
		}
		return artistDtos;		
	}
	
	@Transactional(readOnly = false)
	public void deleteArtist(Long artistId) {
		Artist artist = findArtistById(artistId);
		artistDao.delete(artist);
	}
}
