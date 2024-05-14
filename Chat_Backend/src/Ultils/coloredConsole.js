// coloredConsole.js

function printColoredConsole(color, data) {
    const colors = {
        reset: '\x1b[0m',
        red: '\x1b[31m',
        green: '\x1b[32m',
        yellow: '\x1b[33m',
        blue: '\x1b[34m',
        violet: '\x1b[35m',
        cyan: '\x1b[36m'
    };

    if (colors[color]) {
        console.log(colors[color], data, colors.reset);
    } else {
        console.log('Màu không hợp lệ');
    }
}

module.exports = printColoredConsole;
