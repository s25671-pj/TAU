import unittest

from tau_3_game import generate_board, move_player

class TestGame(unittest.TestCase):

    def test_generate_board(self):
        board_size = 5
        obstacles_count = 5
        board, start_pos, end_pos = generate_board(board_size, obstacles_count)

        self.assertEqual(board[start_pos[0]][start_pos[1]], "A")
        self.assertEqual(board[end_pos[0]][end_pos[1]], "B")
        self.assertNotEqual(start_pos, end_pos)

        obstacle_count = sum(row.count("X") for row in board)
        self.assertEqual(obstacle_count, obstacles_count)

    def test_move_player_valid(self):
        board = [
            [" ", " ", " ", " ", " "],
            [" ", "X", " ", " ", " "],
            [" ", " ", "A", " ", " "],
            [" ", " ", "X", " ", " "],
            [" ", " ", " ", " ", "B"],
        ]
        player_pos = (2, 2)

        new_pos = move_player("d", player_pos, board)
        self.assertEqual(new_pos, (2, 3))

        new_pos = move_player("w", new_pos, board)
        self.assertEqual(new_pos, (1, 3))

        new_pos = move_player("a", new_pos, board)
        self.assertEqual(new_pos, (1, 2))

        new_pos = move_player("s", new_pos, board)
        self.assertEqual(new_pos, (2, 2))

    def test_move_player_invalid(self):
        board = [
            [" ", " ", "A", " ", " "],
            [" ", "X", "X", " ", " "],
            [" ", " ", " ", " ", " "],
            [" ", " ", "X", " ", " "],
            [" ", " ", " ", " ", "B"],
        ]
        player_pos = (0, 2)

        new_pos = move_player("s", player_pos, board)
        self.assertEqual(new_pos, player_pos)

        new_pos = move_player("w", player_pos, board)
        self.assertEqual(new_pos, player_pos)

    def test_end_game(self):
        board = [
            [" ", " ", "A", " ", "B"],
            [" ", "X", "X", " ", " "],
            [" ", " ", " ", " ", " "],
            [" ", " ", "X", " ", " "],
            [" ", " ", " ", " ", " "],
        ]
        player_pos = (0, 2)
        end_pos = (0, 4)

        new_pos = move_player("d", player_pos, board)
        self.assertNotEqual(new_pos, end_pos)

        new_pos = move_player("d", new_pos, board)
        self.assertEqual(new_pos, end_pos)

    def test_illegal_move(self):
        board = [
            [" ", " ", "A", " ", "B"],
            [" ", "X", "X", " ", " "],
            [" ", " ", " ", " ", " "],
            [" ", " ", "X", " ", " "],
            [" ", " ", " ", " ", " "],
        ]
        player_pos = (0, 2)

        new_pos = move_player("dw", player_pos, board)
        self.assertEqual(new_pos, player_pos)

if __name__ == "__main__":
    unittest.main()
