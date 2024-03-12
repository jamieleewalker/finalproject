package music.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

}
