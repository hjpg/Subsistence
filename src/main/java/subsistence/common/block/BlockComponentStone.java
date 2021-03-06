package subsistence.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import subsistence.Subsistence;
import subsistence.common.block.prefab.SubsistenceMultiBlock;
import subsistence.common.util.ArrayHelper;

import java.util.List;

public class BlockComponentStone extends SubsistenceMultiBlock {

    public static final int NETHER_RIND = 0;

    private static final String[] NAMES = new String[]{"nether_rind"};
    private IIcon[] icons;

    public BlockComponentStone() {
        super(Material.rock, 1.0F, 1.0F);
        setStepSound(soundTypeStone);

        setHarvestLevel("pickaxe", 0, 0); //nether rind
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        switch (world.getBlockMetadata(x, y, z)) {
            case 0:
                return 2.5F; // Cobblestone is 2.0
            default:
                return super.getBlockHardness(world, x, y, z);
        }
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        switch (world.getBlockMetadata(x, y, z)) {
            case 0:
                return 10F; // Cobblestone is 10.0
            default:
                return super.getBlockHardness(world, x, y, z);
        }
    }

    @Override
    public int[] getSubtypes() {
        return ArrayHelper.getArrayIndexes(NAMES); // Forces all aspects of this block to base themselves off the NAMES array
    }

    @Override
    public String getNameForType(int type) {
        return ArrayHelper.safeGetArrayIndex(NAMES, type);
    }

    @Override
    public boolean useCustomRender() {
        return false;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= NAMES.length) {
            meta = 0;
        }

        return icons[meta];
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[NAMES.length];
        for (int i = 0; i < NAMES.length; i++) {
            icons[i] = register.registerIcon(Subsistence.RESOURCE_PREFIX + "component/" + NAMES[i]);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int meta = 0; meta < NAMES.length; ++meta) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
