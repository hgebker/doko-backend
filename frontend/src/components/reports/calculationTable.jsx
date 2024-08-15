import DataTableColumn from '@salesforce/design-system-react/components/data-table/column';

import BaseTable from 'components/base/baseTable';
import BaseTableNumberCell from 'components/base/baseTableNumberCell';

const COLUMNS = [
  <DataTableColumn key="type" label="Typ" property="type" />,
  <DataTableColumn key="tim" label="Tim" property="tim">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="jan" label="Jan" property="jan">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="ole" label="Ole" property="ole">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="hannes" label="Hannes" property="hannes">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="louisa" label="Louisa" property="louisa">
    <BaseTableNumberCell />
  </DataTableColumn>
];

const CALCULATION_TYPES = [
  { key: 'sum', label: 'Summe' },
  { key: 'avg', label: 'Schnitt' },
  { key: 'min', label: 'Minimum' },
  { key: 'max', label: 'Maximum' }
];

const resultsToRows = semesterResults => {
  return CALCULATION_TYPES.map(type => {
    return semesterResults.reduce(
      (dict, result) => {
        dict[result.player] = result[type.key];
        return dict;
      },
      { type: type.label }
    );
  });
};

export default function CalculationTable({ items }) {
  return <BaseTable items={resultsToRows(items)} columns={COLUMNS} maxHeight="40vh" />;
}
