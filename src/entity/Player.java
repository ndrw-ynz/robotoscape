package entity;

import level.Level;
import tile.TileManager;
import utility.Atlas;

import java.awt.image.BufferedImage;

import static utility.PlayUtils.*;

/**
 * The Player class is an entity that is controlled
 * by the player throughout the game.
 */
public class Player extends Entity {
    /** isMovingLeft indicates if the player is moving left.*/
    private boolean isMovingLeft;
    /** isMovingRight indicates if the player is moving right.*/
    private boolean isMovingRight;
    /** isJumping indicates if the player is in a jumping state.*/
    private boolean isJumping;
    /** isCharging indicates if the player is in a charging state.*/
    private boolean isCharging;
    /** isShooting indicates if the player is in a shooting state.*/
    private boolean isShooting;
    /** isOnAir indicates if the player is on air.*/
    private boolean isOnAir;
    /** JUMP_SPEED contains the players jump height limit.*/
    private final float JUMP_SPEED = -2.4f*entityScale;
    /** GRAVITY contains the gravity of the player.*/
    private final float GRAVITY = 0.06f*entityScale;
    /** airSpeed contains the speed  of the player on air.*/
    private float airSpeed = 0.0f;
    /** FALL_SPEED_AFTER_COLLISION contains the speed of the player after collision on air.*/
    private final float FALL_SPEED_AFTER_COLLISION = 0.3f * entityScale;

    /**
     * Player is an entity controlled by the player throughout the duration
     * of the game.
     * @param xPosition The starting x-coordinate position of the player.
     * @param yPosition The starting y-coordinate position of the player.
     * @param bitWidth The width of the player sprite in pixels.
     * @param bitHeight The height of the player sprite in pixels.
     * @param entityScale The scale value scaling the appearance of the player.
     */
    public Player(int xPosition, int yPosition, int bitWidth, int bitHeight, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition-13, bitWidth, bitHeight, entityScale, maxNumberOfHearts); // -13 on y for allowance as it spawns, so it doesn't possibly clip on floor.
        movementSpeed = 2f;
        facingRight = true;
        animationCounter = 0;
        animationState = "active";
        getAnimationImages();
        updateHitBox();
        isOnAir = true;
    }

    /**
     * updateHitBox | Updates the hit box of the player.
     */
    @Override
    protected void updateHitBox() {
        int hitBoxWidth = 0; // Stores the width of the player's hit box according to the current animation state.
        int hitBoxHeight = 0 ; // Stores the height of the player's hit box according to the current animation state.

        switch (animationState) {
            case "idle" -> {
                xHitBoxDelta = (int) (13*entityScale);
                yHitBoxDelta = (int) (6*entityScale);
                hitBoxWidth = 11;
                hitBoxHeight = 17;
            }
            case "wake", "active", "damaged" -> {
                xHitBoxDelta = (int) (13*entityScale);
                yHitBoxDelta = (int) entityScale;
                hitBoxWidth = 11;
                hitBoxHeight = 21;
            }
            case "move", "jump" -> {
                xHitBoxDelta = (int) (13*entityScale);
                yHitBoxDelta = (int) entityScale;
                hitBoxWidth = 12;
                hitBoxHeight = 21;
            }
            case "charge", "shoot" -> {
                xHitBoxDelta = (int) (12*entityScale);
                yHitBoxDelta = (int) entityScale;
                hitBoxWidth = 12;
                hitBoxHeight = 22;
            }
            case "dash" -> {
                // TODO: Configure how dash animation is displayed.
                hitBoxWidth = 21;
                hitBoxHeight = 23;
                xHitBoxDelta = 28;
                yHitBoxDelta = 4;
            }
            case "death" -> {

            }
        }
        hitBox.x = (int) (entityCoordinate.x + xHitBoxDelta);
        hitBox.y = (int) (entityCoordinate.y + yHitBoxDelta);
        hitBox.width = hitBoxWidth;
        hitBox.height = hitBoxHeight;
   }

    /**
     * updateMovement | Updates the movement of the player.
     * @param level The current level of the game.
     * @param tileManager The TileManager containing data about the tiles of the game/level.
     */
    @Override
    protected void updateMovement(Level level, TileManager tileManager) {
        if (isMovingLeft || isMovingRight || isJumping || isOnAir) {
            float xSpeed = 0;
            float ySpeed = 0;

            if (isMovingLeft) {
                xSpeed = -movementSpeed;
                facingRight = false;
            }
            if (isMovingRight) {
                xSpeed = movementSpeed;
                facingRight = true;
            }
            if (isJumping) {
                if(!isOnAir) {
                    isOnAir = true;
                    airSpeed = JUMP_SPEED;
                }
            }
            if (!isOnAir) {
                isOnAir = !isEntityOnFloor(entityCoordinate.x, entityCoordinate.y, this, level, tileManager);
            }

            // if cant move, there must be extra space between border and hitBox. calculate that space and set as xSpeed.
            if (isOnAir) {
                // ON AIR. X AND Y AFFECTED.
                if (canEntityMove(entityCoordinate.x, entityCoordinate.y +airSpeed, this, level, tileManager)) {
                    // focus on up and down speed
                    ySpeed = airSpeed;
                    airSpeed += GRAVITY;
                } else {
                    ySpeed = getEntityYOffset(entityCoordinate.y + yHitBoxDelta, airSpeed);
                    if (airSpeed > 0) {
                        isOnAir = false;
                        airSpeed = 0;
                    } else {
                        airSpeed = FALL_SPEED_AFTER_COLLISION;
                    }
                }
            }

            if (!canEntityMove(entityCoordinate.x +xSpeed, entityCoordinate.y, this, level, tileManager)) {
                xSpeed = getEntityXOffset(entityCoordinate.x + xHitBoxDelta, isMovingLeft);
            }

            if (canEntityMove(entityCoordinate.x + xSpeed, entityCoordinate.y + ySpeed, this, level, tileManager)) {
                entityCoordinate.x += xSpeed;
                entityCoordinate.y += ySpeed;
            }
        }
    }

    /**
     * updateAnimation | Updates the animation frames of the player
     * with its animation states.
     */
    @Override
    protected void updateAnimation() {
        // TODO: Improve how player animations are handled based on state of player.
        if (isMovingLeft || isMovingRight) {
            animationState = "move";
        } else if (isOnAir) {
            animationState = "jump";
        } else if (isCharging) {
            animationState = "charge";
        } else if (isShooting) {
            animationState = "shoot";
        } else {
            animationState = "active";
        }

//        if ((isMovingLeft || isMovingRight) && isCharging) {
//            animationState = "charge";
//        }

        animationCounter += 0.05;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
            isShooting = false;
        }
    }

    /**
     * getAnimationImages | Fetches the animation states and images of the entity.
     */
    protected void getAnimationImages() {
        int xImg = 4; // The x-coordinate position of the sub-image containing the sprite image.
        int yImg = 2; // The y-coordinate position of the sub-image containing the sprite image.

        // Adds the idle state of the player and fetches its corresponding animation images.
        animations.put("idle", new BufferedImage[1]);
        animations.get("idle")[0] = Atlas.getSpriteAtlas(Atlas.PLAYER_STATIC_IDLE).getSubimage(xImg, yImg, bitWidth, bitHeight);

        // Adds the wake state of the player and fetches its corresponding animation images.
        animations.put("wake", new BufferedImage[5]);
        for (int i = 0; i < 5; i++) {
            animations.get("wake")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_WAKE).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }

        // Adds the active state of the player and fetches its corresponding animation images.
        animations.put("active", new BufferedImage[1]);
        animations.get("active")[0] = animations.get("wake")[4];

        // Adds the move state of the player and fetches its corresponding animation images.
        animations.put("move", new BufferedImage[8]);
        for (int i = 0; i < 8; i++) {
            animations.get("move")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_MOVE).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }

        // Adds the jump state of the player and fetches its corresponding animation images.
        animations.put("jump", new BufferedImage[8]);
        for (int i = 0; i < 8; i++) {
            animations.get("jump")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_JUMP).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }

        // Adds the charge state of the player and fetches its corresponding animation images.
        animations.put("charge", new BufferedImage[4]);
        for (int i = 0; i < 4; i++) {
            animations.get("charge")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_CHARGE).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }

        // Adds the shoot state of the player and fetches its corresponding animation images.
        animations.put("shoot", new BufferedImage[4]);
        for (int i = 0; i < 4; i++) {
            animations.get("shoot")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_SHOOT).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }

        // Adds the dash state of the player and fetches its corresponding animation images.
        animations.put("dash", new BufferedImage[7]);
        for (int i = 0; i < 7; i++) {
            animations.get("dash")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_GAS_DASH).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }

        // Adds the damaged state of the player and fetches its corresponding animation images.
        animations.put("damaged", new BufferedImage[2]);
        for (int i = 0; i < 2; i++) {
            animations.get("damaged")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_DAMAGED).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }

        // Adds the death state of the player and fetches its corresponding animation images.
        animations.put("death", new BufferedImage[6]);
        for (int i = 0; i < 6; i++) {
            animations.get("death")[i] = Atlas.getSpriteAtlas(Atlas.PLAYER_DEATH).getSubimage(xImg, i*26+yImg, bitWidth, bitHeight);
        }
    }

    /**
     * setIsJumping | Sets the boolean value of the player's isJumping state.
     * @param isJumping A boolean value determining if the player is jumping.
     */
    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    /**
     * setIsMovingLeft | Sets the boolean value of the player's isMovingLeft state.
     * @param isMovingLeft A boolean value determining if the player is moving left.
     */
    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    /**
     * setIsMovingRight | Sets the boolean value of the player's isMovingRight state.
     * @param isMovingRight A boolean value determining if the player is moving right.
     */
    public void setIsMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }

    /**
     * setIsCharging | Sets the boolean value of the player's isCharging state.
     * @param isCharging A boolean value determining if the player is in a charging state.
     */
    public void setIsCharging(boolean isCharging) {this.isCharging = isCharging;}

    /**
     * setIsShooting | Sets the boolean value of the player's isShooting state.
     * @param isShooting A boolean value determining if the player is in a shooting state.
     */
    public void setIsShooting(boolean isShooting) {this.isShooting = isShooting;}
}