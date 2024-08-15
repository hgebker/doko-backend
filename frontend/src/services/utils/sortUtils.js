const SORT_OPTIONS = {
  UP: 'up',
  DOWN: 'down'
};
Object.freeze(SORT_OPTIONS);

const sortBy = (field, reverse, primer) => {
  const key = x => (primer ? primer(x[field]) : x[field]);

  return (a, b) => {
    a = key(a);
    b = key(b);
    return reverse * ((a > b) - (b > a));
  };
};

const sortObjectArray = (array, fieldName, sortDirection) => {
  const arrayClone = [...array];
  const reverse = sortDirection === SORT_OPTIONS.UP ? 1 : -1;

  return arrayClone.sort(sortBy(fieldName, reverse));
};

export { sortObjectArray, sortBy, SORT_OPTIONS };
