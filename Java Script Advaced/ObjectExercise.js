function solve(input){
    let heroes = [];
    for (let line of input) {
        let[name,level,items] = line.split(' / ');
        level = Number(level);
        items = items ? items.split(', ') : [] ;
        heroes.push({name,level,items})
    }
    return JSON.stringify(heroes);
}

function makingJuice(input){
    let juices = {};
    let bottles = {};
    input.forEach(element => {
        let [ name , quantity ] =  element.split(' => ');
        quantity = Number(quantity);s
        if(!juices.hasOwnProperty(name)){
            juices[name] = 0;
        }
        juices[name] += quantity;
        if(juices[name] >= 1000){
            bottles[name] = Math.trunc(juices[name] / 1000 );
        }
    });
    return bottles;
}
