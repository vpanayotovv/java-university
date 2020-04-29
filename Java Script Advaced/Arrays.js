function print(input) {
    let separator = input[input.length - 1]
    input.splice(input.length - 1, 1)
    console.log(input.join(separator))
}

function printNum(input) {
    let lastNum = +input.pop();
    for (let i = 0; i < input.length; i += lastNum) {
        console.log(input[i])
    }
}

function addAndRemove(input) {
    let counter = 0;
    let newArr = [];
    for (let i = 0; i < input.length; i++) {
        counter++;
        if (input[i] === 'add') {
            newArr.push(counter);
        } else if (input[i] === 'remove') {
            newArr.pop();
        }
    }
    if (newArr.length == 0) {
        return console.log('Empty')
    } else {

        return console.log(newArr.join('\n').trim());
    }
}
function rotateArray(input) {
    let rotations = (+input[input.length - 1]) % (input.length - 1)
    let arr = input.slice(0, input.length - 1)
    for (let i = 0; i < +rotations; i++) {
        arr.unshift(arr.pop())
    }
    console.log(arr.join(' '))
}
function extract(input) {

    let output = input.reduce((acc, curr) => {
        const lastElement = acc[acc.length - 1]
        if (curr >= lastElement || lastElement === undefined) {
            acc.push(curr);
        }
        return acc;
    }, [])
    console.log(output.join('\n'))
}
function sortString(input) {
    input.sort((a, b) => {
        if (a.length === b.length) {
            return a.localeCompare(b)
        } else {
            return a.length - b.length
        }
    })
    console.log(input.join('\n'))
}
function magicMatrix(matrix) {
    for (let i = 0; i < matrix.length - 1; i++) {
        let sumROne = matrix[i].reduce((a, b) => a + b, 0);
        let sumRTwo = matrix[i + 1].reduce((a, b) => a + b, 0);
        let sumCOne = 0;
        let sumCTwo = 0;

        for (let j = 0; j < matrix.length; j++) {
            sumCOne += matrix[i][j];
            sumCTwo += matrix[i + 1][j];
        }

        if (sumROne !== sumRTwo || sumCOne !== sumCTwo) {
            return console.log('false');
        }
    }
    return console.log('true');
}