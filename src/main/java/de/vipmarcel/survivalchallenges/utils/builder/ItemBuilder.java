package de.vipmarcel.survivalchallenges.utils.builder;

import com.google.common.collect.Lists;
import de.vipmarcel.survivalchallenges.team.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        meta.displayName(Component.text(name));
        return this;
    }

    public ItemBuilder setTeamName(String name, Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        meta.displayName(Component.text().append(Component.text("§8» ")).append(Component.text(name)).color(TextColor.color(r, g, b)).build());
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        List<Component> newLore = Lists.newArrayList();

        for(String line : lore) {
            newLore.add(Component.text(line));
        }

        meta.lore(newLore);
        return this;
    }

    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return item;
    }

}
