import React from 'react'

const DieFace = ({ value }) => {
    const renderDots = (val) => {
        const dots = []
        const patterns = {
            1: [4],
            2: [0, 8],
            3: [0, 4, 8],
            4: [0, 2, 6, 8],
            5: [0, 2, 4, 6, 8],
            6: [0, 2, 3, 5, 6, 8]
        }

        const pattern = patterns[val] || []
        for (let i = 0; i < 9; i++) {
            if (pattern.includes(i)) {
                dots.push(<div key={i} className="w-1.5 h-1.5 bg-gray-800 rounded-full" />)
            } else {
                dots.push(<div key={i} className="w-1.5 h-1.5" />)
            }
        }
        return dots
    }

    return (
        <div className="w-12 h-12 bg-white border-2 border-gray-200 rounded-lg shadow-sm flex items-center justify-center p-1.5">
            <div className="grid grid-cols-3 grid-rows-3 gap-0.5 w-full h-full">
                {value >= 1 && value <= 6 ? renderDots(value) : (
                    <div className="col-span-3 row-span-3 flex items-center justify-center text-gray-800 font-bold">
                        {value}
                    </div>
                )}
            </div>
        </div>
    )
}

const Dice = ({ value, rolling }) => {
    // If value is an object with die1/die2, show both. If it's just a number, show that.
    const die1 = value?.die1 || (typeof value === 'number' && value <= 6 ? value : null)
    const die2 = value?.die2
    const total = value?.total || (typeof value === 'number' ? value : null)

    return (
        <div className={`flex items-center gap-2 p-3 bg-gray-50 rounded-2xl border border-gray-200 shadow-inner ${rolling ? 'animate-bounce' : ''}`}>
            {die1 && <DieFace value={die1} />}
            {die2 && <DieFace value={die2} />}
            {!die1 && !die2 && total && <div className="text-2xl font-bold px-4 py-2 bg-white rounded-lg border-2 border-gray-200">{total}</div>}
            {!value && <div className="text-2xl font-bold text-gray-300 px-4 py-2 bg-white rounded-lg border-2 border-gray-200">?</div>}
        </div>
    )
}

export default Dice