package org.pickwicksoft.mountainfever.pixabay;

import org.junit.jupiter.api.Test;
import ru.blizzed.pixabaylib.PixabayCallException;
import ru.blizzed.pixabaylib.PixabayErrorException;
import ru.blizzed.pixabaylib.model.PixabayImage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageFinderTest {

    @Test
    void search() throws PixabayErrorException, PixabayCallException {
        ImageFinder searcher = new ImageFinder();
        List<PixabayImage> result = searcher.search("mountain");
        assertTrue(result.size() > 10);
    }
}