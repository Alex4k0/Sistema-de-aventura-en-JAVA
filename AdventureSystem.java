import java.util.Scanner;

public class AdventureSystem
{
    enum HeroClass { WARRIOR, MAGE, ARCHER };

    public static void main(String[] args)
    {
        int slot=0;
        String logResult="";
        Scanner sc = new Scanner(System.in);
        Boolean cont = true;
        String[] backpack = new String[5];
        for (int i = 0; i < backpack.length; i++)
        {
            backpack[i] = null;
        }

            System.out.print("Choose your class (Warrior | Mage | Archer): ");
            String className = sc.nextLine().toUpperCase().trim();

            HeroClass hero;

            hero = HeroClass.valueOf(className);
        while (cont)
        {
            System.out.print("Enter command (Action:Target:Element) | (LOOT:Item_Name): ");
            String[] command = sc.nextLine().trim().split(":");

            StringBuilder adventureLog = new StringBuilder();
            adventureLog.insert(0, "=== START OF QUEST ===\n");

            if (command[0].toUpperCase().startsWith("ATTACK") && command.length == 3)
            {
                if (command[1].toUpperCase().contains("EVIL"))
                {
                    command[1] = command[1].toUpperCase().replace("EVIL", "ENEMEY");
                }

                String prefix = hero.toString().substring(0, 3);

                logResult+=adventureLog.append("[").append(prefix).append("] Attacked ").append(command[1].toUpperCase()).append(" using ").append(command[2].toUpperCase()).append(" power.\n") +"\n";
            }
            else if (command[0].toUpperCase().startsWith("LOOT") && command.length == 2)
            {
                if(backpack[backpack.length-1]==null)
                {
                    backpack[slot]=command[1];
                    System.out.println("Item " + command[1] + " added to slot " + (slot+1) + "/5");
                }
                else
                {
                    System.out.println("Inventory full! Cannot loot " + command[1]);
                }
                slot++;

            }
            else
            {
                System.out.println("Invalid combat command");
            }

            adventureLog.append("Inventory: ");

            for (int i = 0; i < backpack.length; i++)
            {
                if (backpack[i] != null)
                {
                    adventureLog.append(backpack[i]);
                }
                else
                {
                    adventureLog.append("EMPTY");
                }

                if (i < backpack.length-1)
                {
                    adventureLog.append(" | ");
                }
            }

            adventureLog.append("\n");

            System.out.print("--- ADVENTURE LOG ---\n\n");
            System.out.println(logResult.toString() + "\n" + adventureLog.toString());

            System.out.print("Do you want to continue? [Y/N]: ");
            if (!(sc.nextLine().toLowerCase().startsWith("y")))
            {
                cont = false;
            }
        }
    }
}
