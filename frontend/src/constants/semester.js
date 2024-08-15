const LIST_OPTIONS = [
  {
    id: 'ws1819',
    label: 'Wintersemester 18/19'
  },
  {
    id: 'ss19',
    label: 'Sommersemester 19'
  },
  {
    id: 'ws1920',
    label: 'Wintersemester 19/20'
  },
  {
    id: 'ss20',
    label: 'Sommersemester 20'
  },
  {
    id: 'ws2021',
    label: 'Wintersemester 20/21'
  },
  {
    id: 'ss21',
    label: 'Sommersemester 21'
  },
  {
    id: 'ws2122',
    label: 'Wintersemester 21/22'
  },
  {
    id: 'ss22',
    label: 'Sommersemester 22'
  },
  {
    id: 'ws2223',
    label: 'Wintersemester 22/23'
  },
  {
    id: 'ss23',
    label: 'Sommersemester 23'
  }
];

const SEMESTER_OPTIONS = [
  {
    id: 'semester',
    label: 'Semester',
    items: LIST_OPTIONS
  }
];

const SEMESTER_LABEL = LIST_OPTIONS.reduce((dict, option) => {
  return {
    ...dict,
    [option.id]: option.label
  };
}, {});

export { LIST_OPTIONS, SEMESTER_OPTIONS, SEMESTER_LABEL };
