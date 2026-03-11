/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'life2-blue': '#3b82f6',
        'life2-green': '#10b981',
        'life2-yellow': '#f59e0b',
        'life2-red': '#ef4444',
        'life2-purple': '#8b5cf6',
      },
    },
  },
  plugins: [],
}