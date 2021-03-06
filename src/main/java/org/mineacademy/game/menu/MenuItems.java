package org.mineacademy.game.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.exception.FoException;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.tool.Tool;
import org.mineacademy.game.item.ExplosiveArrow;
import org.mineacademy.game.item.ExplosiveBow;
import org.mineacademy.game.item.Gold;

import lombok.Getter;

public final class MenuItems extends Menu {

	@Getter
	private static final MenuItems instance = new MenuItems();

	private final List<ItemStack> tools;

	private MenuItems() {
		super(null);

		setSize(9 * 1);
		setTitle("Items Menu");

		this.tools = compile(new Object[] {
				ExplosiveBow.getItem(),
				ExplosiveArrow.getItem(),
				//BigRocket.getInstance().getItem(),
				Gold.getInstance()
		});
	}

	@Override
	public ItemStack getItemAt(int slot) {
		return slot < tools.size() ? tools.get(slot) : null;
	}

	@Override
	public void onMenuClick(Player pl, int slot, InventoryAction action, ClickType click, ItemStack cursor, ItemStack item, boolean cancelled) {
		final ItemStack is = getItemAt(slot);

		if (is != null)
			pl.getInventory().addItem(is);
	}

	private List<ItemStack> compile(Object[] tools) {
		final List<ItemStack> list = new ArrayList<>();

		for (final Object tool : tools) {
			ItemStack it;

			if (tool != null) {
				if (tool instanceof ItemStack)
					it = (ItemStack) tool;

				else if (tool instanceof Tool)
					it = ((Tool) tool).getItem();

				else if (tool instanceof Number)
					it = new ItemStack(Material.AIR);

				else
					throw new FoException("unknown: " + tool);
			} else
				it = new ItemStack(Material.AIR);

			list.add(it);

		}

		return list;
	}

	@Override
	protected int getInfoButtonPosition() {
		return 9 - 1;
	}

	@Override
	protected String[] getInfo() {
		return new String[] {
				"Use the following items",
				"in &fclasses &7or &fupgrades &7to",
				"enhance the in-game experience."
		};
	}
}
