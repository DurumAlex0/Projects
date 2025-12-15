using System;

class Program
{
    static void Main()
    {
        char[] b = { '1','2','3','4','5','6','7','8','9' };
        char player = 'X';

        while (true)
        {
            Console.Clear();
            PrintBoard(b);

            Console.Write($"\nTurno de {player}. Elige 1-9: ");
            if (!int.TryParse(Console.ReadLine(), out int choice) || choice < 1 || choice > 9)
            {
                Console.WriteLine("Entrada inválida.");
                Console.ReadKey();
                continue;
            }

            int i = choice - 1;
            if (b[i] == 'X' || b[i] == 'O')
            {
                Console.WriteLine("Casilla ocupada.");
                Console.ReadKey();
                continue;
            }

            b[i] = player;

            if (Win(b, player))
            {
                Console.Clear();
                PrintBoard(b);
                Console.WriteLine($"\n¡Gana {player}!");
                break;
            }

            if (Draw(b))
            {
                Console.Clear();
                PrintBoard(b);
                Console.WriteLine("\n¡Empate!");
                break;
            }

            player = (player == 'X') ? 'O' : 'X';
        }

        Console.WriteLine("\nPulsa una tecla para salir...");
        Console.ReadKey();
    }

    static void PrintBoard(char[] b)
    {
        Console.WriteLine($" {b[0]} | {b[1]} | {b[2]} ");
        Console.WriteLine("---+---+---");
        Console.WriteLine($" {b[3]} | {b[4]} | {b[5]} ");
        Console.WriteLine("---+---+---");
        Console.WriteLine($" {b[6]} | {b[7]} | {b[8]} ");
    }

    static bool Win(char[] b, char p)
    {
        return
            (b[0] == p && b[1] == p && b[2] == p) ||
            (b[3] == p && b[4] == p && b[5] == p) ||
            (b[6] == p && b[7] == p && b[8] == p) ||
            (b[0] == p && b[3] == p && b[6] == p) ||
            (b[1] == p && b[4] == p && b[7] == p) ||
            (b[2] == p && b[5] == p && b[8] == p) ||
            (b[0] == p && b[4] == p && b[8] == p) ||
            (b[2] == p && b[4] == p && b[6] == p);
    }

    static bool Draw(char[] b)
    {
        for (int i = 0; i < 9; i++)
            if (b[i] != 'X' && b[i] != 'O')
                return false;
        return true;
    }
}
