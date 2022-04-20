package org.pickwicksoft.mountainfever.pixabay;

import ru.blizzed.pixabaylib.Pixabay;
import ru.blizzed.pixabaylib.PixabayCallException;
import ru.blizzed.pixabaylib.PixabayErrorException;
import ru.blizzed.pixabaylib.model.PixabayImage;
import ru.blizzed.pixabaylib.params.ImageTypeParam;
import ru.blizzed.pixabaylib.params.OrientationParam;
import ru.blizzed.pixabaylib.params.PixabayParams;

import java.util.List;

public class ImageFinder {

    private Integer page = 1;

    public ImageFinder() {
        Pixabay.initialize("22717211-9b9be34deef0a69fa423a0129");
    }

    public List<PixabayImage> search(String term) throws PixabayErrorException, PixabayCallException {
        List<PixabayImage> result = Pixabay.search().image(term,
                        PixabayParams.ORIENTATION.of(OrientationParam.Orientation.HORIZONTAL),
                        PixabayParams.IMAGE_TYPE.of(ImageTypeParam.Type.PHOTO),
                        PixabayParams.PAGE.of(page))
                .execute()
                .getHits()
                .stream()
                .toList();
        return result;
    }

    public void nextPage() {
        page++;
    }

}
