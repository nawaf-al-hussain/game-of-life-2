import React, { useMemo } from 'react'
import { Stage, Layer, Rect, Group } from 'react-konva'
import useGameStore from '../store/gameStore'
import AnimatedPlayer from './AnimatedPlayer'
import FloatingText from './FloatingText'
import Tile from './Tile'

const TILE_SIZE = 80
const TILE_SPACING = 120
const TILES_PER_ROW = 11
const BOARD_WIDTH = 1400
const BOARD_HEIGHT = 1300

function GameBoard({ onTileClick, width = 800, height = 600 }) {
  const game = useGameStore((state) => state.game)
  const effects = useGameStore((state) => state.effects)
  const removeEffect = useGameStore((state) => state.removeEffect)

  const scaleX = width / BOARD_WIDTH
  const scaleY = height / BOARD_HEIGHT
  const scale = Math.min(scaleX, scaleY) * 0.95

  const tiles = useMemo(() => game?.tiles || [], [game?.tiles])

  const getTilePosition = (index) => {
    const row = Math.floor(index / TILES_PER_ROW)
    let col = index % TILES_PER_ROW
    if (row % 2 === 1) col = TILES_PER_ROW - 1 - col
    return {
      x: 50 + col * TILE_SPACING,
      y: 80 + row * TILE_SPACING,
    }
  }

  const players = game?.players || []

  // Only allow MONEY and ATTRIBUTE effects in Konva — action cards are now HTML dialogs
  const safeEffects = effects.filter(e => e.type === 'MONEY' || e.type === 'ATTRIBUTE')

  const groupOffset = {
    x: (width - BOARD_WIDTH * scale) / 2,
    y: (height - BOARD_HEIGHT * scale) / 2,
  }

  return (
    <Stage width={width} height={height}>
      {/* Background */}
      <Layer listening={false}>
        <Group scaleX={scale} scaleY={scale} x={groupOffset.x} y={groupOffset.y}>
          <Rect x={-100} y={-100} width={BOARD_WIDTH + 200} height={BOARD_HEIGHT + 200} fill="#f8fafc" />
        </Group>
      </Layer>

      {/* Tiles */}
      <Layer>
        <Group scaleX={scale} scaleY={scale} x={groupOffset.x} y={groupOffset.y}>
          {tiles.map((tile, index) => (
            <Tile
              key={tile.id || index}
              tile={tile}
              index={index}
              position={getTilePosition(index)}
              onTileClick={onTileClick}
            />
          ))}
        </Group>
      </Layer>

      {/* Players + floating text effects */}
      <Layer>
        <Group scaleX={scale} scaleY={scale} x={groupOffset.x} y={groupOffset.y}>
          {players.map((player, index) => (
            <AnimatedPlayer
              key={player.id || `player-${index}`}
              player={player}
              index={index}
              getTilePosition={getTilePosition}
              totalTiles={tiles.length}
            />
          ))}

          {safeEffects.map((effect) => {
            const { x, y } = getTilePosition(effect.position || 0)
            return (
              <FloatingText
                key={effect.id}
                x={x + TILE_SIZE / 2}
                y={y}
                text={effect.text}
                color={effect.color}
                onComplete={() => removeEffect(effect.id)}
              />
            )
          })}
        </Group>
      </Layer>
    </Stage>
  )
}

export default GameBoard