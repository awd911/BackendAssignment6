package com.example.getmesocialservice;

import com.example.getmesocialservice.model.Album;
import com.example.getmesocialservice.service.AlbumService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AlbumServiceTests {

	@Autowired
	private AlbumService albumService;

	@Before
	public void saveAlbum(){
		albumService.saveAlbum(new Album(1,"Tom","Tom","PhotoURL","Now"));
	}

	@Test
	public void getAlbum(){
		Album album = albumService.getAlbumById(1);
		Assert.assertEquals("Tom",album.getName());
	}

	@Test
	public void editAlbum() {
		Album album = albumService.getAlbumById(1);
		album.setName("Bob");
		albumService.saveAlbum(album);

		Assert.assertEquals("Tom",album.getName());
	}

}
