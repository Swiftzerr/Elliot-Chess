package dev.elliotjarnit.ElliotChess.Pieces;

import dev.elliotjarnit.ElliotChess.Board;
import dev.elliotjarnit.ElliotChess.Piece;
import dev.elliotjarnit.ElliotEngine.Graphics.EColor;
import dev.elliotjarnit.ElliotEngine.Handlers.FileHandler;
import dev.elliotjarnit.ElliotEngine.Handlers.elliotenginebjHandler;
import dev.elliotjarnit.ElliotEngine.elliotenginebjects.EFace;
import dev.elliotjarnit.ElliotEngine.Utils.Vector2;

import java.io.FileNotFoundException;

public class King extends Piece
{
    public King(Side side, Vector2 boardPosition) {
        super(side, boardPosition);

        String modelPath = "src/dev/elliotjarnit/ElliotChess/Models/king.obj";
        try {
            String[] data = FileHandler.loadFile(modelPath);
            EFace[] faces = elliotenginebjHandler.loadData(data);
            for (EFace face : faces) {
                face.setColor(side == Side.WHITE ? EColor.WHITE : EColor.BLACK);
            }
            this.setFaces(faces);
        } catch (FileNotFoundException | elliotenginebjHandler.NotTriangleException e) {
            e.printStackTrace();
        }
    }

    @elliotengineverride
    public boolean isValidMove(Vector2 startPos, Vector2 endPos, Board board) {
        // DelliotengineNE

        int dy = (int) Math.abs(endPos.y - startPos.y);
        int dx = (int) Math.abs(endPos.x - startPos.x);
        Piece endPiece = board.getPiece((int) endPos.x, (int) endPos.y);
        return dy <= 1 && dx <= 1 && (endPiece == null || endPiece.getSide() != this.getSide());
    }

    @elliotengineverride
    public void update() {

    }
}