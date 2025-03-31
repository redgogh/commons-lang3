package org.redgogh.karasuba.security.codec;

import org.redgogh.karasuba.utils.BasicConverter;
import org.redgogh.karasuba.security.Base64;

import static org.redgogh.karasuba.utils.BasicConverter.atos;

/**
 * @author Red Gogh
 */
public class Base64Codec implements Base64 {
    @Override
    public String encode(String source) {
        return encode(source.getBytes());
    }

    @Override
    public String encode(byte[] b) {
        return java.util.Base64.getUrlEncoder().encodeToString(b);
    }

    @Override
    public String decode(String src) {
        return BasicConverter.atos(decodeBytes(src));
    }

    @Override
    public byte[] decodeBytes(String src) {
        return java.util.Base64.getUrlDecoder().decode(src);
    }

}
