package com.bergerkiller.bukkit.common.reflection.classes;

import java.io.File;
import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.RegionFile;
import net.minecraft.server.RegionFileCache;

import com.bergerkiller.bukkit.common.reflection.SafeField;

public class RegionFileCacheRef {
	public static final SafeField<Map<File, Reference<RegionFile>>> filesField = new SafeField<Map<File, Reference<RegionFile>>>(RegionFileCache.class, "a");
	public static final Map<File, Reference<RegionFile>> FILES;
	static {
		FILES = filesField.isValid() ? filesField.get(null) : new HashMap<File, Reference<RegionFile>>();
	}

	/**
	 * Gets a region file from file without creating a new instance
	 * 
	 * @param file to get the RegionFile of
	 * @return the Region File, or null if not loaded
	 */
	public static RegionFile getFile(File file) {
		Reference<RegionFile> ref = FILES.get(file);
		if (ref != null) {
			return ref.get();
		} else {
			return null;
		}
	}
}