package bkcraft.bedwars.world.schematic;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Nullable;

import bkcraft.bedwars.world.jnbt.Tag;

public abstract class NBTSchematicReader {

   protected static Tag requireTag(Map<String, Tag> items, String key) throws IOException {
       if (!items.containsKey(key)) {
           throw new IOException("Schematic file is missing a \"" + key + "\" tag");
       }

       Tag tag = items.get(key);

       return tag;
   }

   @Nullable
   protected static Tag getTag(Map<String, Tag> items, String key) {
       if (!items.containsKey(key)) {
           return null;
       }

       Tag test = items.get(key);
      
       return test;
   }
}