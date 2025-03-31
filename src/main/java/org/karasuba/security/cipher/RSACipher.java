package org.karasuba.security.cipher;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 Red Gogh                                                   *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2025/2/20. */

import org.karasuba.security.Codec;
import org.karasuba.security.RSA;
import org.karasuba.security.key.RSAPrivateKey;
import org.karasuba.security.key.RSAPublicKey;
import org.karasuba.tuple.Pair;
import org.karasuba.utils.Capturer;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import static org.karasuba.utils.BasicConverter.atob;
import static org.karasuba.utils.BasicConverter.atos;

/**
 * @author Red Gogh
 */
@SuppressWarnings("DataFlowIssue")
public class RSACipher implements RSA {

    @Override
    public Pair<RSAPublicKey, RSAPrivateKey> generateKeyPair() {
        return generateKeyPair(2048);
    }

    @Override
    public Pair<RSAPublicKey, RSAPrivateKey> generateKeyPair(int size) {
        KeyPairGenerator keyPairGenerator =
                Capturer.icall(() -> KeyPairGenerator.getInstance("RSA"));
        keyPairGenerator.initialize(size);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return Pair.of(new RSAPublicKey(keyPair.getPublic()), new RSAPrivateKey(keyPair.getPrivate()));
    }

    @Override
    public String encrypt(String message, RSAPublicKey publicKey) {
        return Capturer.call(() -> {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey.toPublicKey());
            byte[] b = cipher.doFinal(atob(message));
            return Codec.BASE64.encode(b);
        });
    }

    @Override
    public String decrypt(String encryptedMessage, RSAPrivateKey privateKey) {
        return Capturer.call(() -> {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey.toPrivateKey());
            byte[] b = cipher.doFinal(Codec.BASE64.decodeBytes(encryptedMessage));
            return atos(b);
        });
    }

}
