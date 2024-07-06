package com.nosh;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.core.api.utils.PolymerRegistry;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagEntry;
import net.minecraft.registry.tag.TagGroupLoader;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinecartImprovements implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	//public static final Block FAST_RAIL = new Block(Block.Settings.create().strength(4.0f));
	//public  static final PoweredRailBlock FAST_RAIL = new PoweredRailBlock(AbstractBlock.Settings.create().strength(4.0f));
	//public static final SimplePolymerBlock FAST_RAIL = new SimplePolymerBlock(SimplePolymerBlock.Settings.create().strength(4.0f))
	public static final FastRail FAST_RAIL = new FastRail(AbstractBlock.Settings.create().noCollision());
	//public static final Block FAST_RAIL = new PoweredRailBlock(AbstractBlock.Settings.create().noCollision());


	//public static final TagKey<Block> TAG_POWERED_RAILS = TagKey.of(
	//		Registry.BLOCK_KEY, new Identifier("diamondrails", "powered_rails")
	//);


	@Override
	public void onInitialize() {

		Registry.register(Registries.BLOCK, Identifier.of("minecart-improvements", "fast_rail"), FAST_RAIL);
		Registry.register(Registries.ITEM, Identifier.of("minecart-improvements", "fast_rail"), new PolymerBlockItem(FAST_RAIL, new Item.Settings(), Items.POWERED_RAIL));

//		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
//			content.addAfter(Items.RAIL, new BlockItem(new FastRail(AbstractBlock.Settings.create()), new Item.Settings()));
//		});
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


	}
}