package com.example.miroslavfacebookproject.service.implementation;
import com.example.miroslavfacebookproject.dto.AlbumImagesDTO;
import com.example.miroslavfacebookproject.dto.ImageUploadDTO;
import com.example.miroslavfacebookproject.entity.AlbumImage;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.AlbumImageRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.AlbumImageService;
import com.example.miroslavfacebookproject.service.contract.UploadImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class AlbumImageServiceImpl implements AlbumImageService {

    private final UserRepository userRepository;
    private final UploadImageService uploadImageService;
    private final AlbumImageRepository albumImageRepository;

    public AlbumImageServiceImpl(UserRepository userRepository, UploadImageService uploadImageService, AlbumImageRepository albumImageRepository) {
        this.userRepository = userRepository;
        this.uploadImageService = uploadImageService;
        this.albumImageRepository = albumImageRepository;
    }

    @Override
    public void addAlbumImage(User currentUser, ImageUploadDTO imageUploadDTO) {
        AlbumImage albumImage = new AlbumImage(uploadImageService.takeImageURL(), imageUploadDTO.getImageDescription());
        albumImageRepository.save(albumImage);
        User user = userRepository.findUserById(currentUser.getId());
        user.getAlbumImages().add(albumImage);
        userRepository.save(user);
    }

    @Override
    public ModelAndView takeAlbumImages(User currentUser) {
        User user = userRepository.findUserById(currentUser.getId());
        AlbumImagesDTO albumImagesDTO = new AlbumImagesDTO();
        albumImagesDTO.setAlbumImages(user.getAlbumImages());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("albumImagesDTO", albumImagesDTO.getAlbumImages());
        return modelAndView;
    }
}
