package com.redgogh.cache;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 RedGogh                                                    *|
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

import com.redgogh.tools.cache.JvmLocalCache;
import com.redgogh.tools.cache.LocalCache;
import com.redgogh.tools.time.TimeUnitOperator;
import org.junit.Test;

/**
 * @author RedGogh
 */
@SuppressWarnings("ALL")
public class JvmCacheTest {

    @Test
    public void cacheTest() {
        LocalCache cache = new JvmLocalCache();
        cache.set("a1", "张三", 1, TimeUnitOperator.SECONDS);
        cache.set("a2", "张三", 2, TimeUnitOperator.SECONDS);
        cache.set("a3", "张三", 3, TimeUnitOperator.SECONDS);
        cache.set("a4", "张三", 4, TimeUnitOperator.SECONDS);
        cache.set("a5", "张三", 5, TimeUnitOperator.SECONDS);
        cache.set("a6", "张三", 6, TimeUnitOperator.SECONDS);

        while(true);

    }

}
